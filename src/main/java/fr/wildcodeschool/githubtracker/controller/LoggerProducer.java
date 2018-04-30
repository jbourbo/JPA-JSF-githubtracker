package fr.wildcodeschool.githubtracker.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton @ApplicationScoped
public class LoggerProducer
{

    @Produces
    public Logger getLogger(final InjectionPoint ip)
    {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }
}