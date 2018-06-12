 // if( !border.contains( wormHead )){
           // outOfBoundsResult = true;
      //  }

//System.out.println( currentLength ) ;

// System.out.println("z= " + z ) ;

 //System.out.println(border.getWidth());
 
  public boolean outOfBounds(){
        
    //   return true or false depending on whether the snake head is outside border
    //      use the .contains method for border to see if the Location of the 
    //        head is within or without
     if( !border.contains( body[0] )){
        outOfBounds =  true;
        }
    
    double topBorder = border.getY(),
           bottomBorder = topBorder + border.getHeight(),
           leftBorder = border.getX(),
           rightBorder = leftBorder + border.getWidth(); 
     
     Location wormHead = body[0].getLocation();
     
        if( ( wormHead.getX() < leftBorder ) || 
            ( wormHead.getX() >= rightBorder ) ||
            ( wormHead.getY() < topBorder ) ||
            ( wormHead.getY() >= bottomBorder ) ){
                 outOfBounds =  true;
        }
        return outOfBounds;

    }
    
    
       public void begin() {
        
        JPanel southPanel = new JPanel();
        JPanel subPanel = new JPanel();
        
        JPanel controlPanel = new JPanel(new GridLayout(3,1));
        Container contentPane = getContentPane();
        contentPane.add( southPanel, BorderLayout.SOUTH);
        contentPane.validate();
    }
    
    