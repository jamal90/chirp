package com.intuit.chirp.tweets.repository;

import com.intuit.chirp.tweets.model.dto.UserFollowing;
import com.intuit.chirp.tweets.model.domain.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLdapId(String ldapId);

    /**
     * get all users except the current logged-in user
     * @param userId logged-in userid
     * @return list of users
     */
    List<User> findAllByIdNot(long userId);

    @Query("""
        select
            u.id,
            u.first_name,
            u.last_name,
            u.email,
            case
                when f.follower_id = :userId then true
                else false
            end as is_following
          from
            users u
          left outer join "following" f
            on
            u.id = f.following_id
          where
            f.follower_id = :userId
            or f.follower_id is null
            and u.id != :userId
    """)
    List<UserFollowing> userWithFollowingStatus(@Param("userId") long userId);

}
