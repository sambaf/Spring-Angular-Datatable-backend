package org.sambasoft.repositories;

import org.sambasoft.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository  extends JpaRepository<Contact, Long>{
  
  @Query("select c from  Contact c where c.firstName like :x")
  public Page<Contact> search(@Param("x")String keyword,Pageable pageable);
}
