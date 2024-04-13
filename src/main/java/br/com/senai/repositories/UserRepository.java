package br.com.senai.repositories;

import br.com.senai.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<Users,Long>{

}
