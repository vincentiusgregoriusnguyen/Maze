import java.util.ArrayList;

// based on http://stackoverflow.com/questions/9191428/maze-solving-algorithm-in-c/9192067#9192067


public class Main {
	
	static char Maze[][] ={
		{'#',' ','#','#','#','#','#','#','#'},
		{'#',' ',' ',' ','#',' ',' ',' ','#'},
	    {'#',' ','#','#','#',' ','#',' ','#'},
	    {'#',' ','#',' ',' ',' ','#',' ','#'},
	    {'#',' ','#',' ','#',' ','#','#','#'},
	    {'#',' ',' ',' ','#',' ','#',' ','#'},
	    {'#',' ','#','#','#',' ','#',' ','#'},
	    {'#',' ',' ',' ','#',' ',' ',' ','#'},
	    {'#','#','#','#','#','#','#',' ','#'}
};
	
	static int Height = Maze.length;
    static int Width = Maze[0].length;
    static Coordinate Start = new Coordinate(Maze[0].length - 2,Maze.length - 1);
    static Coordinate End = new Coordinate(1,0);
    static char CurrentDirection = 'n';
    
    static int previousX = 0;
    static int previousY = 0;
    static int currentX = Maze[0].length - 2;
    static int currentY = Maze.length - 1;
    static ArrayList<String> instructions = new ArrayList<String>();

	static char Wall = '#';
	static char Free = ' ';
	static char Path = '*';
	
	
	public static void findpath(){
		while(currentX != End.getX() && currentY != End.getY()){
				switch(CurrentDirection){
				case 'n': 
							if(currentY - 1 >= 0 && (Maze[currentY - 1][currentX] == '*') || (Maze[currentY - 1][currentX] == 'E')){ 
								currentY = currentY - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Forward");
								break;
							}
							if(currentX - 1 >= 0 && (Maze[currentY][currentX - 1] == '*') || (Maze[currentY][currentX - 1] == 'E')){
								currentX = currentX - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Left");
								CurrentDirection = 'w';
								break;
							}
							if(currentX + 1 < Width && (Maze[currentY][currentX + 1] == '*') || (Maze[currentY][currentX + 1] == 'E')){
								currentX = currentX + 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Right");
								CurrentDirection = 'e';
								break;
							}
				case 'e':
							if(currentX + 1  < Width && (Maze[currentY][currentX + 1] == '*') || (Maze[currentY][currentX + 1] == 'E')){
								currentX = currentX - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								CurrentDirection = 'e';
								instructions.add("Forward");
								break;
							}
							if(currentY - 1 >= 0 && (Maze[currentY - 1][currentX] == '*') || (Maze[currentY - 1][currentX] == 'E')){
								currentY = currentY - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Left");
								CurrentDirection = 'n';
								break;
							}
							if(currentY + 1 < Height && (Maze[currentY + 1][currentX] == '*') || (Maze[currentY + 1][currentX] == 'E')){
								currentY = currentY + 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Right");
								CurrentDirection = 's';
								break;
							}
				case 's':	
							if(currentY + 1 < Height && (Maze[currentY + 1][currentX] == '*') || (Maze[currentY + 1][currentX] == 'E')){
								currentY = currentY + 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Forward");
								break;
							}
							if(currentX + 1 < Width && (Maze[currentY][currentX + 1] == '*') || (Maze[currentY][currentX + 1] == 'E')){
								currentX = currentX + 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Left");
								CurrentDirection = 'e';
								break;
							}
							if(currentX - 1 >= 0 && (Maze[currentY][currentX - 1] == '*') || (Maze[currentY][currentX - 1] == 'E')){
								currentX = currentX - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Right");
								CurrentDirection = 'w';
								break;
							}
				case 'w':
							if(currentY - 1 >= 0 && (Maze[currentY - 1][currentX] == '*') || (Maze[currentY - 1][currentX] == 'E')){
								currentY = currentY - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Right");
								CurrentDirection = 'n';
								break;
							}
							if(currentX - 1 >= 0 && (Maze[currentY][currentX - 1] == '*') || (Maze[currentY][currentX - 1] == 'E')){
								currentX = currentX - 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Forward");
								break;
							}
							if(currentY + 1 < Height && (Maze[currentY+1][currentX] == '*') || (Maze[currentY+1][currentX] == 'E')){
								currentY = currentY + 1;
								System.out.println("y: " + currentY + " x: " + currentX);
								instructions.add("Left");
								CurrentDirection = 's';
								break;
							}	
				}
		}
	} 
	
	public static void printmaze(){
		for(int i = 0; i < Maze.length; i++){
			for(int j = 0; j < Maze.length; j++){
				System.out.print(Maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean solve(int x, int y){
		if(x == Start.getX() && y == Start.getY()){
			Maze[y][x] = 'S';
		} else if (x == End.getX() && y == End.getY()){
			Maze[y][x] = 'E';
		}else{
		Maze[y][x] = Path;
		}
		
		if(x == End.getX() && y == End.getY()){
			return true;
		}
		
		if(x > 0 && Maze[y][x - 1] == Free && solve(x - 1, y)){
			return true;
		}
		if(x < Width && Maze[y][x+1] == Free && solve(x+1,y)){
			return true;
		}
		if(y > 0 && Maze[y - 1][x] == Free && solve(x,y - 1)){
			return true;
		}
		if(y < Height && Maze[y + 1][x] == Free && solve(x,y+1)){
			return true;
		}
		
		Maze[y][x] = Free;
		
		return false;
	}
	
	public static void main(String[] pikachu){
		System.out.println("Maze");		
		
	    printmaze();
		
		System.out.println();
		
		if(solve(Start.getX(), Start.getY())){
			System.out.println("Solution");
			printmaze();
			
			System.out.println("y: " + Start.getY() + " x: " + Start.getY());
			findpath();
			
			for(String x: instructions){
				System.out.println(x);
			}
			System.out.println("y: " + currentY + " x: " + currentX);
			System.out.println(CurrentDirection);
		} 
		else{
			System.out.println("no solution");
		}
	}

}
