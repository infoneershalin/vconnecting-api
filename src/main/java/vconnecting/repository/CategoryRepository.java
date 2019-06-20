package vconnecting.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import vconnecting.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Transactional
	void deleteByParentCategoryId(int id);
}
