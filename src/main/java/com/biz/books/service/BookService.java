package com.biz.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.books.mapper.BookDao;
import com.biz.books.model.BookVO;

@Service
public class BookService {

	@Autowired
	BookDao bookMapper;
	
	
	public int insertDB(BookVO vo) {
	
		int ret = bookMapper.insert(vo);
		return ret;
	}

	public List<BookVO> selectAll() {

		List<BookVO> memoList = bookMapper.selectAll();
		return memoList;
	
	}

	public BookVO getMemo(long id) {
		
		BookVO vo = bookMapper.findById(id);
		return vo;
	
	}

	public int delete(long id) {
		// TODO Auto-generated method stub
		int ret = bookMapper.delete(id);
		return ret;
	}

	public int writeDB(BookVO vo) {
		
		long id = vo.getB_id();
		int ret = 0;
		if(id > 0) ret = bookMapper.update(vo);
		else ret = bookMapper.insert(vo);

		return ret;
	}



}
