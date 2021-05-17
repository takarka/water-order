package io.mersys.basqar.config;

import java.util.List;

import io.mersys.basqar.config.db.AbstractMongoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;



@Configuration
// @Profile(value = {"dev", "prod"})
public class MongodbConfig extends AbstractMongoConfig
{
    public MongodbConfig()
    {
        super("basqar-servvice");
    }

//    @Override
//    public void customConvertions(List<Converter<?, ?>> convertions)
//    {
//
//    }

}
