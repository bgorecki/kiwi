package kiwi.dijkstra;

import kiwi.models.DbLotniskoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: scroot
 * Date: 03.06.13
 * Time: 21:22
 */
public class Vertex
{
	protected Integer length;
	protected Vertex from;
	protected Edge edge;
	protected Boolean visited;
	protected String name;
	List<Edge> next = new ArrayList<Edge>();
	protected DbLotniskoEntity lotnisko;

	public Edge getEdge()
	{
		return edge;
	}

	public Vertex withEdge(final Edge edge)
	{
		this.edge = edge;
		return this;
	}


	public Vertex withLotnisko(final DbLotniskoEntity lotnisko)
	{
		this.lotnisko = lotnisko;
		return this;
	}

	public DbLotniskoEntity getLotnisko()
	{
		return lotnisko;
	}

	public List<Edge> getNext()
	{
		return next;
	}

	public Vertex withLength(final Integer length)
	{
		this.length = length;
		return this;
	}

	public Vertex withFrom(final Vertex from)
	{
		this.from = from;
		return this;
	}

	public Vertex withVisited(final Boolean visited)
	{
		this.visited = visited;
		return this;
	}

	public Vertex withName(final String name)
	{
		this.name = name;
		return this;
	}

	public Integer getLength()
	{
		return length;
	}

	public Vertex getFrom()
	{
		return from;
	}

	public Boolean getVisited()
	{
		return visited;
	}

	public String getName()
	{
		return name;
	}
}
