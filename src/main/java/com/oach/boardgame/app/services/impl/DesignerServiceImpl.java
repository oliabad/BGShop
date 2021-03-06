package com.oach.boardgame.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oach.boardgame.app.models.BoardGame;
import com.oach.boardgame.app.models.Designer;
import com.oach.boardgame.app.repositories.IDesignerRepository;
import com.oach.boardgame.app.services.IDesignerService;

@Service
public class DesignerServiceImpl implements IDesignerService {
	
	@Autowired
	private IDesignerRepository repository;

	@Override
	public Designer createDesigner(Designer designer) {
		return repository.insert(designer);
	}

	@Override
	public List<Designer> getAllDesigners() {
		return repository.findAll();
	}

	@Override
	public Designer getDesignerById(String id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Designer updateDesignerById(Designer designer) {
		if (repository.existsById(designer.getId())) {
			return repository.save(designer);
		}
		return null;
	}

	@Override
	public boolean deleteDesignerById(String id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Designer addToDesignerNewBoardGame(Designer designer, BoardGame boardGame) {
		if(repository.existsById(designer.getId()) && repository.existsById(boardGame.getId())) {
			List<BoardGame> boardGames = new ArrayList<>();
			boardGames.add(boardGame);
			designer.setBoardGames(boardGames);
			return repository.save(designer);
			}
		return null;
	}

}
