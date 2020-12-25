package com.evan.demo.service;

import com.evan.demo.dao.BookDAO;
import com.evan.demo.dao.CategoryDAO;
import com.evan.demo.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryService categoryService;

    public List<Book> list() {
        return bookDAO.findAll(new Sort(Sort.Direction.DESC,"id"));
    }

    public void addOrUpdate(Book book) {
        // 主键存在时更新数据，主键不存在时插入数据
        bookDAO.save(book);
    }

    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        return bookDAO.findAllByCategory(categoryService.get(cid));
    }

    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
