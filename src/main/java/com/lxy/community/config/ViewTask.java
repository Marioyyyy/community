package com.lxy.community.config;

import com.lxy.community.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ViewTask extends QuartzJobBean {

        @Autowired
        ViewService viewService;

        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        @Override
        protected void executeInternal(JobExecutionContext jobExecutionContext)
        throws JobExecutionException {

            log.info("ViewTask-------- {}", sdf.format(new Date()));

            //将 Redis 里的点赞信息同步到数据库里
            viewService.transViewCountFromRedis2DB();
        }
    }
