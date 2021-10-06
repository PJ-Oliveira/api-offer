package com.pj.offer.config.batch;

import com.pj.offer.config.batch.processor.OfferItemProcessor;
import com.pj.offer.domain.model.Offer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @ConfigurationProperties(prefix = "datasource.consumerappointment")
    public DataSource consumerAppointmentDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public BatchConfigurer configurer(DataSource batchDataSource){
        return new DefaultBatchConfigurer(batchDataSource);
    }


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasource.batch")
    public DataSource dataSource(){
        final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/offer");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("12345");

        return driverManagerDataSource;
    }


    @Bean
    public JdbcCursorItemReader<Offer> reader(){
        JdbcCursorItemReader<Offer> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource());
        reader.setSql("SELECT * FROM public.offer");
        reader.setRowMapper(new OfferRowMapper());
        return reader;
    }


    public OfferItemProcessor processor(){
        return new OfferItemProcessor();

    }

    @Bean
    public FlatFileItemWriter<Offer> flatFileItemWriter(){
        FlatFileItemWriter<Offer> flatFileItemWriter = new FlatFileItemWriter<Offer>();
        flatFileItemWriter.setResource(new ClassPathResource("output.csv"));

        DelimitedLineAggregator<Offer> agregator = new DelimitedLineAggregator<Offer>();
        agregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Offer> fieldExtractor = new BeanWrapperFieldExtractor<Offer>();
        fieldExtractor.setNames(new String[]{"id", "inicio", "fim", "descricao", "desconto", "active"});
        agregator.setFieldExtractor(fieldExtractor);

        flatFileItemWriter.setLineAggregator(agregator);
        return flatFileItemWriter;
    }


    @Bean
    public Step executeStep(){
        return stepBuilderFactory.get("executeStep").<Offer, Offer>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(flatFileItemWriter())
                .build();

    }

    @Bean
    public Job exportOfferJob(){
        return jobBuilderFactory.get("exportOfferJob").incrementer(new RunIdIncrementer()).flow(executeStep()).end().build();
    }





}

