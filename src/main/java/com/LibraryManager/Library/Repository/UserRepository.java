package com.LibraryManager.Library.Repository;


import com.LibraryManager.Library.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
