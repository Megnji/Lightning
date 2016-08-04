package bean;

public class Connection {

	//index of point a
	public int _pa;
	
	//index of point b
	public int _pb;
	
	//type of connection
	public enum ConnectionType{host,embedding,Q};
	public ConnectionType _type = ConnectionType.host;
	
	public double _weight = 0;
	public Connection(int pa,int pb){
		_pa = pa;
		_pb = pb;
	}
	
	public Connection(int pa,int pb, ConnectionType type){
		this(pa,pb);
		_type = type;
	}
	
	public Connection(int pa,int pb, Double weight){
		this(pa,pb);
		_weight = weight;
	}
	
	public Connection(int pa,int pb, ConnectionType type, Double weight){
		this(pa,pb);
		_type = type;
		_weight = weight;
	}
	
	public Connection(int pa,int pb, ConnectionType type, int weight){
		this(pa,pb);
		_type = type;
		_weight = weight;
	}
}
