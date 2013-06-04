package kiwi.dijkstra;

import kiwi.models.DbLotEntity;
import kiwi.models.DbLotniskoEntity;

import java.util.*;

import static java.util.HashMap.*;

/**
 * User: scroot
 * Date: 03.06.13
 * Time: 21:23
 */
public class Finder
{
	Map<String, Vertex> vertex = new HashMap<String, Vertex>();
	Map<Edge, Integer> edges = new HashMap<Edge, Integer>();
	private Integer orgLen;
	private Edge removed;



	public Map<String, Vertex> getVertex()
	{
		return vertex;
	}

	public Map<Edge, Integer> getEdges()
	{
		return edges;
	}

	public Finder(List<DbLotEntity> loty)
	{
		for(DbLotEntity i: loty) {
			if(!vertex.containsKey(i.getLotniskoByWylot().getNazwa())) {
				DbLotniskoEntity lotnisko = i.getLotniskoByWylot();
				vertex.put(lotnisko.getNazwa(), new Vertex().withName(lotnisko.getNazwa()).withLength(9999).withLotnisko(lotnisko));
			}
			if(!vertex.containsKey(i.getLotniskoByPrzylot().getNazwa())) {
				DbLotniskoEntity lotnisko = i.getLotniskoByPrzylot();
				vertex.put(lotnisko.getNazwa(), new Vertex().withName(lotnisko.getNazwa()).withLength(9999).withLotnisko(lotnisko));
			}

			Edge edge = new Edge().withBegin(vertex.get(i.getLotniskoByWylot().getNazwa())).withEnd(vertex.get(i.getLotniskoByPrzylot().getNazwa())).withLength(i.getCzasPodrozy()).withLot(i);
			edges.put(edge, i.getCzasPodrozy());
			vertex.get(i.getLotniskoByWylot().getNazwa()).getNext().add(edge);
		}
	}

	public List<Vertex> Find(DbLotniskoEntity from, DbLotniskoEntity to) {
		Vertex start = vertex.get(from.getNazwa());
		List<Vertex> visit = new ArrayList<Vertex>();
		start.withVisited(true);

		for(Edge i: start.getNext()) {
			visit.add(i.getEnd().withLength(i.getLength()).withFrom(start).withEdge(i));
		}

		while(visit.size()>0) {
			Collections.sort(visit, new Comparator<Vertex>()
			{
				@Override
				public int compare(Vertex o1, Vertex o2)
				{
					if(o1.getLength() == o2.getLength()) return 0;
					if(o1.getLength() > o2.getLength()) return 1;
					return -1;
				}
			});

			Vertex act = visit.get(0);
			visit.remove(0);
			for(Edge i: act.getNext()) {

				if(act.getEdge() != null && act.getEdge().getLot().getGodzinaPrzylotu().getTime() > i.getLot().getGodzinaWylotu().getTime()) continue;

				if(!visit.contains(i.getEnd())) visit.add(i.getEnd());
				if(i.getEnd().getLength() > act.getLength()+i.getLength()) {
					i.getEnd().withLength(act.getLength()+i.getLength()).withFrom(act).withEdge(i);
				}
				if(i.getEnd().getName().equals(to.getNazwa())) {
					break;
				}
			}
		}

		if(vertex.get(to.getNazwa()).getLength() < 9999) {
			Vertex v = vertex.get(to.getNazwa());
			List<Vertex> path = new ArrayList<Vertex>();
			path.add(v);
			while((v = v.getFrom()) != null) {
				path.add(v);
			}
			return path;
		}
		return null;
	}

	public void reset() {
		for(Vertex i: vertex.values()) {
			i.withFrom(null).withVisited(false).withLength(9999).withEdge(null);
		}
	}

	public void removeEdge(Vertex from, Vertex to) {
		for(Edge i: edges.keySet()) {
			if(i.getBegin() == from && i.getEnd() == to) {
				this.removed = i;
				this.orgLen = i.getLength();
				i.withLength(9999);
				break;
			}
		}
	}

	public void restore() {
		this.removed.withLength(this.orgLen);
		this.removed = null;
		this.orgLen = null;
	}
}
