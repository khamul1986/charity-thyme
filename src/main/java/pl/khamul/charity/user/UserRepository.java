package pl.khamul.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);
}
