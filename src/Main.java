// based on http://stackoverflow.com/questions/9191428/maze-solving-algorithm-in-c/9192067#9192067


public class Main {
	
	static int Height = 9;
    static int Width = 9;
    
    static Coordinate Start = new Coordinate(1,0);
    static Coordinate End = new Coordinate(7,8);
	
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
	
	static char Wall = '#';
	static char Free = ' ';
	static char Path = '*';
	
	public static void printmaze(){
		for(int i = 0; i < Maze.length; i++){
			for(int j = 0; j < Maze.length; j++){
				System.out.print(Maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean solve(int x, int y){
		Maze[y][x] = Path;
		
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
		}
		else{
			System.out.println("no solution");
		}
	}

}
