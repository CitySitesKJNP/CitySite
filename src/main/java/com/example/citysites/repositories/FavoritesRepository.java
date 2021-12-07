//package com.example.citysites.repositories;
//
//import com.example.citysites.models.Favorite;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface FavoritesRepository extends JpaRepository<Favorite, Long> {
//    // custom query using sql
//     @Query(value = "DELETE FROM favorites  WHERE activity_id = :activityId", nativeQuery = true)
//    // custom query example using jpql
////    @Query("SELECT w FROM Widget w WHERE LOWER(w.widgetName) like LOWER(:widget_search_name)")
//    void deleteFavoritesByActivityId(@Param("activityId") long activityId);
//}
