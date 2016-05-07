Composants:
BoardModel *3 playboard, compboard, visuboard
bool[5] *2 PlayShipSet, CompShipSet
switch (ship type)
4 boutons pour les 4 directions
turn
skillLevel compLVL=BEGINNER;
bool[10][10] compshots=false;
coor2D lastCompHit;
Direction dir=NORTH;

initialisation:
new BoardModel(Available) *2

        ships placement:
        select ship type (switch)
        select start case ->cellModel startcell
        si ship/= submarine
                show available directions
                        testDirection(cellModel startcell, direction dir, ShipType bateau) {
                            bool ok=true;
                            cellModel test=startcell;
                            int i=0;
                            while(i<bateau.size-1 && ok) {
                                if (adjacentCell(test, dir).CellType==Available) {
                                    i++
                                    test=(adjacentCell(test, dir)
                                }
                                else ok=false;
                            }
                            return ok;
                        }
        select direction
        placeShip(cellModel startcell, direction dir, ShipType bateau) {
            cellModel cell=startcell;
            for i=0 i<bateau.getsize {
                cell.setCellType(shipTypeToCellType(bateau));
                cell=(adjacentCell(cell, dir);
            }
        }
        replaceAll(available,ocean)


        pour Ordi: randomCell(available, true) pour startcell

        pour visuboard :replaceALL(available, unknown)


Jeu:
    coord2D coord;
    lastCompHit=coord2D(playboard.BOARD_SIZE+1,playboard.BOARD_SIZE+1);
if turn==players {
        get cell clicked->cellModel cellclicked
        coord=visuboard.cellCoord(cellclicked);
        if compboard.getcellmodel(coord.row,coord.column).getCellType().isAShip() {
            compfleet.updateHits(cellTypeToShipType(compboard.getcellmodel(coord.row,coord.column).getCellType()))
            compboard.getcellmodel(coord.row,coord.column).setCellType(HIT);
            visuboard.getcellmodel(coord.row,coord.column).setCellType(HIT);
        }
        else {
            visuboard.getcellmodel(coord.row,coord.column).setCellType(OCEAN);
        }
        turn=COMPUTER;
}

//bool[10][10] compshots
//coor2D lastCompHit

else {// turn==computer
    if compLVL==BEGINER {
        bool test=false;
        while !test {
            coord=playboard.cellCoords(playboard.randomCell(AVAILABLE, false));
            if !compshots[coord.row][coord.column] {
                compshots[coord.row][coord.column]=true;
                test=true;
                if playboard.getcellmodel(coord.row,coord.column).getCellType().isAShip() {
                    playfleet.updateHits(cellTypeToShipType(playboard.getcellmodel(coord.row,coord.column).getCellType()))
                    playboard.getcellmodel(coord.row,coord.column).setCellType(HIT);
                    lastCompHit=coord;
                }
            }
        }
    } else if compLVL==MEDIUM {
        //Direction dir
        bool test=false;
        if lastCompHit.getRow>playboard.BOARD_SIZE {
            while !test {
                coord=playboard.cellCoords(playboard.randomCell(AVAILABLE, false));
                if !compshots[coord.row][coord.column] {
                    compshots[coord.row][coord.column]=true;
                    test=true;
                    if playboard.getcellmodel(coord.row,coord.column).getCellType().isAShip() {
                        playfleet.updateHits(cellTypeToShipType(playboard.getcellmodel(coord.row,coord.column).getCellType()))
                        playboard.getcellmodel(coord.row,coord.column).setCellType(HIT);
                        lastCompHit=coord;
                    }
                }
            }
        } else {
            coord=lastCompHit;
            bool north,est,south,west=false;
            while !test {
                if dir==NORTH {
                    if coord.getColumn()>0 {
                        coord.setColumn(coord.getColumn()-1)
                    } else {
                        north=true;
                        if !est {dir=EST} else if !south {dir=SOUTH} else if !west {dir=WEST}
                    }
                    if !compshots[coord.row][coord.column] {
                        test=true
                    } else if playboard.getcellmodel(coord.row,coord.column).getCellType()==OCEAN {
                        coord=lastCompHit;
                        north=true;
                        if !est {dir=EST} else if !south {dir=SOUTH} else if !west {dir=WEST}
                    }
                } else if dir==EST {
                    if coord.getRow()<playbord.BOARD_SIZE {
                        coord.setRow(coord.getRow()+1)
                    } else {
                        est=true;
                        if !north {dir=NORTH} else if !south {dir=SOUTH} else if !west {dir=WEST}
                    }
                    if !compshots[coord.row][coord.column] {
                        test=true
                    } else if playboard.getcellmodel(coord.row,coord.column).getCellType()==OCEAN {
                        coord=lastCompHit;
                        est=true;
                        if !north {dir=NORTH} else if !south {dir=SOUTH} else if !west {dir=WEST}
                    }
                } else if dir==SOUTH {
                    if coord.getColumn()<playbord.BOARD_SIZE {
                        coord.setColumn(coord.getColumn()+1)
                    } else {
                        south=true;
                        if !north {dir=NORTH} else if !est {dir=EST} else if !west {dir=WEST}
                    }
                    if !compshots[coord.row][coord.column] {
                        test=true
                    } else if playboard.getcellmodel(coord.row,coord.column).getCellType()==OCEAN {
                        coord=lastCompHit;
                        south=true;
                        if !north {dir=NORTH} else if !est {dir=EST} else if !west {dir=WEST}
                    }
                } else if dir==WEST {
                    if coord.getRow()>0 {
                        coord.setRow(coord.getRow()-1)
                    } else {
                        west=true;
                        if !north {dir=NORTH} else if !south {dir=SOUTH} else if !est {dir=EST}
                    }
                    if !compshots[coord.row][coord.column] {
                        test=true
                    } else if playboard.getcellmodel(coord.row,coord.column).getCellType()==OCEAN {
                        coord=lastCompHit;
                        west=true;
                        if !north {dir=NORTH} else if !south {dir=SOUTH} else if !est {dir=EST}
                    }
                }
                if north && est && south && west {
                    lastCompHit.setRow(playboard.BOARD_SIZE+1);
                    LastCompHit.setColumn(playboard.BOARD_SIZE+1);
                    while !test {
                        coord=playboard.cellCoords(playboard.randomCell(AVAILABLE, false));
                        if !compshots[coord.row][coord.column] {
                            test=true;
                        }
                    }
                }
            }
            compshots[coord.row][coord.column]=true;
            test=true;
            if playboard.getcellmodel(coord.row,coord.column).getCellType().isAShip() {
                playfleet.updateHits(cellTypeToShipType(playboard.getcellmodel(coord.row,coord.column).getCellType()))
                playboard.getcellmodel(coord.row,coord.column).setCellType(HIT);
                lastCompHit=coord;
            }
        }
    }

    turn=PLAYER;
}





















