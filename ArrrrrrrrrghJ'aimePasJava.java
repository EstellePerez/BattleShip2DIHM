package battleship2D.model;


public class Jeu {

    /*=========================================================================*/
    /* Members                                                                 */
    /*=========================================================================*/

    private BoardModel playboard, compboard, visuboard;

    private boolean[5] playShipSets, compShipSets;

    private Turn tour;

    public SkillLevel compLVL=BEGINNER;

    private boolean[10][10] compshots;

    private coord2D lastCompHit;

    private Direction dir=NORTH;

    /*=========================================================================*/
    /* Public methods                                                          */
    /*=========================================================================*/

    // constructor
    public void Jeu() {
        playboard=new BoardModel(AVAILABLE);
        compboard=new BoardModel(AVAILABLE);
        visuboard=new BoardModel(UNKNOWN);
        for (int i=0; i<5; i++) {
            playShipSet[i]=false;
            compShipSet[i]=false;
        }
        for (int row=0; row<10; row++) {
            for (int col=0; col<10; col++) {
                compshots[row][col]=false;
            }
        }
        lastCompHit=coord2D(11,11);
    }

    //placement des bateaux
    public void initialisation() {

    }

    //jeu d'un tour
    public void jeuTour() {
        coord2D coord;
        if (turn==PLAYER) {
            //fonction de l'ui
            coord=UI.getcoord()
            //
            if (compboard.getCellModel(coord.getRow(),coord.getColumn()).getCellType().isAShip()) {
                compfleet.updateHits(cellTypeToShipType(compboard.getCellModel(coord.getRow,coord.getColumn).getCellType()))
                compboard.getCellModel(coord.getRow,coord.getColumn).setCellType(HIT);
                visuboard.getCellModel(coord.getRow,coord.getColumn).setCellType(HIT);
            }
            else {
                visuboard.getCellModel(coord.getRow,coord.getColumn).setCellType(OCEAN);
            }
            turn=COMPUTER;
        }

        //boolean[10][10] compshots
        //coor2D lastCompHit

        else {// turn==computer
            if (compLVL==BEGINER) {
                boolean test=false;
                while (!test) {
                    coord=playboard.cellCoords(playboard.randomCell(AVAILABLE, false));
                    if (!compshots[coord.getRow][coord.getColumn]) {
                        compshots[coord.getRow][coord.getColumn]=true;
                        test=true;
                        if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType().isAShip()) {
                            playfleet.updateHits(cellTypeToShipType(playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()))
                            playboard.getCellModel(coord.getRow,coord.getColumn).setCellType(HIT);
                            lastCompHit=coord;
                        }
                    }
                }
            } else if (compLVL==MEDIUM) {
                //Direction dir
                boolean test=false;
                if (lastCompHit.getRow>playboard.BOARD_SIZE) {
                    while (!test) {
                        coord=playboard.cellCoords(playboard.randomCell(AVAILABLE, false));
                        if (!compshots[coord.getRow][coord.getColumn]) {
                            compshots[coord.getRow][coord.getColumn]=true;
                            test=true;
                            if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType().isAShip()) {
                                playfleet.updateHits(cellTypeToShipType(playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()))
                                playboard.getCellModel(coord.getRow,coord.getColumn).setCellType(HIT);
                                lastCompHit=coord;
                            }
                        }
                    }
                } else {
                    coord=lastCompHit;
                    boolean north,est,south,west=false;
                    while (!test) {
                        if (dir==NORTH) {
                            if (coord.getColumn()>0) {
                                coord.setColumn(coord.getColumn()-1)
                            } else {
                                north=true;
                                if (!est) {dir=EST} else if (!south) {dir=SOUTH} else if (!west) {dir=WEST}
                            }
                            if (!compshots[coord.getRow][coord.getColumn]) {
                                test=true
                            } else if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()==OCEAN) {
                                coord=lastCompHit;
                                north=true;
                                if (!est) {dir=EST} else if (!south) {dir=SOUTH} else if (!west) {dir=WEST}
                            }
                        } else if (dir==EST) {
                            if (coord.getRow()<playbord.BOARD_SIZE) {
                                coord.setRow(coord.getRow()+1)
                            } else {
                                est=true;
                                if (!north) {dir=NORTH} else if (!south) {dir=SOUTH} else if (!west) {dir=WEST}
                            }
                            if (!compshots[coord.getRow][coord.getColumn]) {
                                test=true
                            } else if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()==OCEAN) {
                                coord=lastCompHit;
                                est=true;
                                if (!north) {dir=NORTH} else if (!south) {dir=SOUTH} else if (!west) {dir=WEST}
                            }
                        } else if (dir==SOUTH) {
                            if (coord.getColumn()<playbord.BOARD_SIZE) {
                                coord.setColumn(coord.getColumn()+1)
                            } else {
                                south=true;
                                if (!north) {dir=NORTH} else if (!est) {dir=EST} else if (!west) {dir=WEST}
                            }
                            if (!compshots[coord.getRow][coord.getColumn]) {
                                test=true
                            } else if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()==OCEAN) {
                                coord=lastCompHit;
                                south=true;
                                if (!north) {dir=NORTH} else if (!est) {dir=EST} else if (!west) {dir=WEST}
                            }
                        } else if (dir==WEST) {
                            if (coord.getRow()>0) {
                                coord.setRow(coord.getRow()-1)
                            } else {
                                west=true;
                                if (!north) {dir=NORTH} else if (!south) {dir=SOUTH} else if (!est) {dir=EST}
                            }
                            if (!compshots[coord.getRow][coord.getColumn]) {
                                test=true
                            } else if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()==OCEAN) {
                                coord=lastCompHit;
                                west=true;
                                if (!north) {dir=NORTH} else if (!south) {dir=SOUTH} else if (!est) {dir=EST}
                            }
                        }
                        if (north && est && south && west) {
                            lastCompHit.setRow(playboard.BOARD_SIZE+1);
                            LastCompHit.setColumn(playboard.BOARD_SIZE+1);
                            while (!test) {
                                coord=playboard.cellCoords(playboard.randomCell(AVAILABLE, false));
                                if (!compshots[coord.getRow][coord.getColumn]) {
                                    test=true;
                                }
                            }
                        }
                    }
                    compshots[coord.getRow][coord.getColumn]=true;
                    test=true;
                    if (playboard.getCellModel(coord.getRow,coord.getColumn).getCellType().isAShip()) {
                        playfleet.updateHits(cellTypeToShipType(playboard.getCellModel(coord.getRow,coord.getColumn).getCellType()))
                        playboard.getCellModel(coord.getRow,coord.getColumn).setCellType(HIT);
                        lastCompHit=coord;
                    }
                }
            }

            turn=PLAYER;
        }
    }





}

