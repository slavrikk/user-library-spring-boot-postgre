package ru.service.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.service.library.repo.entity.UserReader;

import java.util.List;

public interface UserRepository extends JpaRepository<UserReader, Integer> {

    @Query(value = "select * from user_reader join subscribe on user_reader.id=subscribe.user_reader_id where subscribe.type=?1",nativeQuery=true )
     List<UserReader> getUserReaderByTypeSubscription(String typeSubscription);

}
