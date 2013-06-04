package kiwi.dijkstra;

import kiwi.models.DbLotEntity;

/**
 * User: scroot
 * Date: 03.06.13
 * Time: 21:25
 */
public class Edge
{

	protected Integer length;
	protected Vertex begin;
	protected Vertex end;

	private DbLotEntity lot;

	public DbLotEntity getLot()
	{
		return lot;
	}

	public Edge withLot(final DbLotEntity lot)
	{
		this.lot = lot;
		return this;
	}

	public Edge withLength(final Integer length)
	{
		this.length = length;
		return this;
	}

	public Edge withBegin(final Vertex begin)
	{
		this.begin = begin;
		return this;
	}

	public Edge withEnd(final Vertex end)
	{
		this.end = end;
		return this;
	}

	public Integer getLength()
	{
		return length;
	}

	public Vertex getBegin()
	{
		return begin;
	}

	public Vertex getEnd()
	{
		return end;
	}
}
