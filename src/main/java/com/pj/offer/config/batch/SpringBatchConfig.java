package com.pj.offer.config.batch;

import com.pj.offer.domain.model.Offer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${file.input}")
    private String fileInput;

    @Bean
    FlatFileItemReader<Offer> fileItemReader(@Value("${file.input}") Resource resource)throws Exception{
        return new FlatFileItemReaderBuilder<Offer>()
                .name("offers.csv")
                .resource(resource)
                .targetType(Offer.class)
                .delimited().delimiter(",").names(new String[]{"products", "inicio", "fim", "descricao", "desconto", "active"})
                .build();
    }

    @Bean
    JdbcBatchItemWriter<Offer> jdbcBatchItemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Offer>()
                .dataSource(dataSource)
                .sql("insert into OFFER(PRODUCTS, INICIO, FIM, DESCRICAO, DESCONTO, ACTIVE) (:products, :inicio, :fim, :descricao, :desconto, :active)")
                .beanMapped()
                .build();
    }


    @Bean
    Job job (JobBuilderFactory jobBuilderFactory,
             StepBuilderFactory stepBuilderFactory,
             ItemReader<? extends Offer> itemReader,
             ItemWriter<? super Offer> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<Offer, Offer>chunk(100)
                .reader(itemReader)
                //.processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("etl")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

}

