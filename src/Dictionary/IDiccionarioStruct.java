package Dictionary;

import java.util.ArrayList;


public interface IDiccionarioStruct {
	
	public ArrayList<Integer> buscar(String s);
	
	public void insertar(String s, int posc);

	public int getSize();
	
}
