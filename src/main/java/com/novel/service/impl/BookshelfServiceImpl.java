package com.novel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.mapper.BookshelfMapper;
import com.novel.pojo.Bookshelf;
import com.novel.pojo.Novel;
import com.novel.service.BookshelfService;

@Service
public class BookshelfServiceImpl implements BookshelfService{
	@Autowired
	private BookshelfMapper bookshelfMapper;
		
	@Override
	public List<Bookshelf> findAllBookshelf(int uid) {
		return bookshelfMapper.findAllBookshelf(uid);
	}

	@Override
	public List<Novel> findAllNovel(int bid) {
		return bookshelfMapper.findAllNovel(bid);
	}

	@Override
	public Bookshelf findBookshelfByBid(int uid, int bid) {
		return bookshelfMapper.findBookshelfByBid(uid, bid);
	}

	@Override
	public int insBookshelf(Bookshelf bookshelf) {
		return bookshelfMapper.insBookshelf(bookshelf);
	}

	@Override
	public int delBookshelf(int id) {
		return bookshelfMapper.delBookshelf(id);
	}
	@Override
	public int updBookshelf(Bookshelf bookshelf) {
		return bookshelfMapper.updBookshelf(bookshelf);
	}

	@Override
	public Bookshelf selByUid(int uid) {
		// TODO Auto-generated method stub
		return bookshelfMapper.selByUid(uid);
	}

	@Override
	public Bookshelf selById(int id) {
		return bookshelfMapper.selById(id);
	}

	@Override
	public int addNovelByBid(int bid, int nid) {
		return bookshelfMapper.addNovelByBidNid(bid, nid);
	}
	@Override
	public int delNovelByBid(int bid, int nid) {
		return bookshelfMapper.delNovelByBidNid(bid, nid);
	}

	@Override
	public int findNovelIsBookshelf(int nid, int uid) {
		return bookshelfMapper.findNovelIsBookshelf(nid, uid);
	}

}
