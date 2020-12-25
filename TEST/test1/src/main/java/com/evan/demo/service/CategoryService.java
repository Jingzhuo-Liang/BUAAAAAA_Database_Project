package com.evan.demo.service;

import com.evan.demo.dao.CategoryDAO;
import com.evan.demo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int id) {
        Category c = categoryDAO.findById(id).orElse(null);
        return c;
    }
}
