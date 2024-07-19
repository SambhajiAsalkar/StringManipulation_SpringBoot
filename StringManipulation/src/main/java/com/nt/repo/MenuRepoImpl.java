package com.nt.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.model.MainModel;

@Repository
public class MenuRepoImpl {
 @Autowired
 private  IMenuRepository repo;
 
 public MainModel insertMenu(MainModel entity) 
 {
	 return repo.save(entity);
 }
}
