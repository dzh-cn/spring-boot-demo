package dong.demoboot.dao;

import dong.demoboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//	Page<User> findAll(Example<? extends User> ex, Pageable pageable);
}
