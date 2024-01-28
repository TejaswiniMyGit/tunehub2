package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepository;

@Service
public class SongServiceImplementation implements SongService {
	@Autowired
	SongRepository repo;
	@Override
	public void addSong(Song song) {
		// TODO Auto-generated method stub
		repo.save(song);
	}
	@Override
	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public boolean songExists(String name) {
		// TODO Auto-generated method stub
		Song song=repo.findByName(name);
		if(song==null) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public void updateSong(Song song) {
		// TODO Auto-generated method stub
		repo.save(song);
	}
	

}
