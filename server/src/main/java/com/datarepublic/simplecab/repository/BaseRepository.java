package com.datarepublic.simplecab.repository;

import com.datarepublic.simplecab.domains.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository <T extends Model, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {
}
