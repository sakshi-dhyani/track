package com.Track.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.Track.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{


	Role findByNameAndActive(String name, byte b);

	Role findByName(String name);


}
