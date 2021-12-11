package com.example.citysites.seed;

import com.example.citysites.models.Activity;
import com.example.citysites.models.ActivityImage;
import com.example.citysites.models.Review;
import com.example.citysites.models.User;
import com.example.citysites.repositories.ActivityImageRepository;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ActivityRepository activityDao;
    private final ReviewRepository reviewDao;
    private final ActivityImageRepository activityImageDao;

    @Value("${app.env}")
    private String environment;

    public DatabaseSeeder(ActivityRepository activityDao, ReviewRepository reviewDao, ActivityImageRepository activityImageDao) {
        this.activityDao = activityDao;
        this.reviewDao = reviewDao;
        this.activityImageDao = activityImageDao;
    }

    private List<Activity> seedActivities() {
        List<Activity> activities = Arrays.asList(
                new Activity("San Antonio Zoo", "12PM - 9PM", "The Zoo in San Antonio! See animals and hang outdoors.", -98.4736266427417F, 29.462558526605523F),
                new Activity("Skyview Dormatory", "N/A", "Event center and school.", -98.47184539303268F, 29.467097270775575F),
                new Activity("McNay Art Museum", "10AM - 6PM", "Works include, but are not limited to, European and American masters.",-98.457504143826F, 29.48751749694531F),
                new Activity("Jack White Park Trailhead", "6AM - 7:30PM", "Wonderful park with central views of the city. Near the San Antonio Zoo.", -98.42415546833644F, 29.453738594798708F),
                new Activity("San Antonio River Walk", "24/7", "The riverwalk in San Antonio.", -98.48472249236681F, 29.425164567079456F)
        );

        activityDao.saveAll(activities);

        return activities;
    }

    private void seedActivityImages(List<Activity> activities) {
        List<ActivityImage> activityImages1 = Arrays.asList(
                new ActivityImage("https://nbc16.com/resources/media/b5bae286-9c42-40ee-8999-b762d3f6330c-large16x9_Timothy_Uma.jpg?1588184557311"),
                new ActivityImage("https://lh5.googleusercontent.com/p/AF1QipO8zD6bcQPOqM8zAzIU7v5CgO33xOvvB63ZHr1y=w426-h240-k-no"),
                new ActivityImage("https://lh5.googleusercontent.com/p/AF1QipM72Dp1ULfvluW9PK8vsWSJzBfJKL4n7gVY9IYz=w408-h272-k-no"),
                new ActivityImage("https://lh5.googleusercontent.com/p/AF1QipNNjLvEmSkXUHkyEvRHGYQWXdpsEOiRnoGbwkED=w408-h302-k-no"),
                new ActivityImage("https://lh5.googleusercontent.com/p/AF1QipM_fKAOTNBioHAEwM-ZtGNwjrEqGGIl-Z8imQo=w408-h306-k-no")
        );

        Random r = new Random();
        for (ActivityImage image : activityImages1) {
            Activity activity = activities.get(r.nextInt(activities.size()));
            image.setActivity(activity);
        }

        activityImageDao.saveAll(activityImages1);
    }

//    private List<Review> seedActivityReviews(List<Activity> activities, List<User> users) {
//        List<Review> activityReviews1 = Arrays.asList(
//                new Review(1, "This zoo is okay. I like the San Diego Zoo better."),
//                new Review(2, "I went to school here and it was not amazing. I got my degree, but I would not attend again."),
//                new Review(3 , "I love the art selection here. I wish the staff was kinder, but I enjoyed the content."),
//                new Review(4, "Great trail! I have hiked, camped, and chilled out here. I truly loved it."),
//                new Review(5 ,"I LOVE THE RIVER WALK! I cannot stop going here, it is SO MUCH FUN! YAY!")
//        );
//
//        Random r = new Random();
//        for (Review review :activityReviews1) {
//            Activity activity = activities.get(r.nextInt(activities.size()));
//            review.setActivityReview(activity);
//        }
//        reviewDao.saveAll(activityReviews1);
//
//        return activityReviews1;
//    }

//    private List<User> seedUsers(List<Review> reviews) {
//        List<User> users = Arrays.asList(
//                new User(),
//                new User(),
//                new User(),
//                new User(),
//                new User()
//        );

//        for ()
//    }



    @Override
    public void run(String... strings) throws Exception {
        if (! environment.equals("development")) {
            log.info("app.env is not development, doing nothing.");
            return;
        }
        log.info("Deleting posts...");
        activityDao.deleteAll();
        log.info("Seeding activites...");
        List<Activity> activities = seedActivities();
        log.info("Seeding images...");
        seedActivityImages(activities);
//        log.info("Seeding reviews...");
//        seedActivityReviews(activities);
        log.info("Finished running seeders!");
    }
}
