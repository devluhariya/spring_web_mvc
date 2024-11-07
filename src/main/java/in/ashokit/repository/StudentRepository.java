package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.studentEntity;

public interface StudentRepository extends JpaRepository<studentEntity, Integer>{
	
}
