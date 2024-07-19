package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.MainModel;

public interface IMenuRepository extends JpaRepository<MainModel, Long>
{

}
