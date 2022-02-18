package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static void main(String[] args) {
        try (InputStream in = AlertRabbit.class
                .getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(config.getProperty("url"),
                                                    config.getProperty("username"),
                                                    config.getProperty("password"));
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connect", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(config.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(5000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

/**    private static Properties init() throws IOException {
        Properties config;
        try (InputStream in = AlertRabbit.class
                .getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            config = new Properties();
            config.load(in);
        }
        return config;
    }

    private static int getIntervalFromProperties() throws IOException {
        return Integer.parseInt(init().getProperty("rabbit.interval"));
    } */

    public static class Rabbit implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connect");
            try (PreparedStatement statement = connection.prepareStatement("insert into rabbit (created_date) values (?)")) {
                statement.setTimestamp(1, Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Rabbit runs here ...");
        }
    }
}