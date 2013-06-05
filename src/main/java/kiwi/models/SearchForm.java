package kiwi.models;

import kiwi.dao.DbKlasaDao;
import kiwi.dao.DbLotniskoEntityDao;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
* User: scroot
* Date: 05.06.13
* Time: 01:38
*/
public class SearchForm
{

	String from;
	protected DbLotniskoEntity fromLotnisko;
	String to;
	protected DbLotniskoEntity toLotnisko;
	DbKlasaEntity klasaDb;
	Integer klasa;
	Integer ilosc;
	Integer ilosc_dz;
	Integer ilosc_inf;
	protected String data;
	protected DateTime dataParsed;
	Boolean direct;
	Map<String, String> errors = new HashMap<String, String>();


    public SearchForm withData(final String data) {
        this.data = data;
        return this;
    }

    public SearchForm withDataParsed(final DateTime dataParsed) {
        this.dataParsed = dataParsed;
        return this;
    }


    public SearchForm withFromLotnisko(final DbLotniskoEntity fromLotnisko) {
        this.fromLotnisko = fromLotnisko;
        return this;
    }

    public SearchForm withToLotnisko(final DbLotniskoEntity toLotnisko) {
        this.toLotnisko = toLotnisko;
        return this;
    }


    public Boolean validate()
	{
		if (from.isEmpty())
		{
			errors.put("from", "Pole lotnisko początkowe nie może być puste");
		} else
		{
			try
			{
				String[] fromEx = from.split(" - ");
				DbLotniskoEntityDao lotnisko = new DbLotniskoEntityDao();
				fromLotnisko = lotnisko.getByName(fromEx[1]);
			} catch (Exception e)
			{
				errors.put("from", "Nieprawidłowe lotnisko");
			}
		}

		if (to.isEmpty())
		{
			errors.put("to", "Pole lotnisko docelowe nie może być puste");
		} else
		{
			try
			{
				String[] fromEx = to.split(" - ");
				DbLotniskoEntityDao lotnisko = new DbLotniskoEntityDao();
				toLotnisko = lotnisko.getByName(fromEx[1]);
			} catch (Exception e)
			{
				errors.put("to", "Nieprawidłowe lotnisko");
			}
		}
		if (klasa == null)
		{
			errors.put("klasa", "Musisz wybrać pole klasa");
		} else {
			klasaDb = new DbKlasaDao().getById(klasa);
		}
		if (ilosc == null)
		{
			errors.put("ilosc", "Musisz podać ilośc pasażerów dorosłych");
		}
		if (ilosc_dz == null)
		{
			errors.put("ilosc_dz", "Musisz podać ilość dzieci");
		}
		if (ilosc_inf == null)
		{
			errors.put("ilosc_inf", "Musisz podać ilość infantów");
		}
		if (!data.matches("\\d{2}/\\d{2}/\\d{4}"))
		{
			errors.put("data", "Musisz podać datę w formacie dd/mm/yyyy");
		}

		if (ilosc + ilosc_dz + ilosc_inf > 12)
		{
			errors.put("ilosc", "Jedna rezerwacja może obejmować maksymalnie 12 pasażerów");
		}

		if (errors.isEmpty()) return true;
		return false;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public Integer getKlasa()
	{
		return klasa;
	}

	public void setKlasa(Integer klasa)
	{
		this.klasa = klasa;
	}

	public Integer getIlosc()
	{
		return ilosc;
	}

	public void setIlosc(Integer ilosc)
	{
		this.ilosc = ilosc;
	}

	public Integer getIlosc_dz()
	{
		return ilosc_dz;
	}

	public void setIlosc_dz(Integer ilosc_dz)
	{
		this.ilosc_dz = ilosc_dz;
	}

	public Integer getIlosc_inf()
	{
		return ilosc_inf;
	}

	public void setIlosc_inf(Integer ilosc_inf)
	{
		this.ilosc_inf = ilosc_inf;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public Boolean getDirect()
	{
		return direct;
	}

	public void setDirect(Boolean direct)
	{
		this.direct = direct;
	}

	public Map<String, String> getErrors()
	{
		return errors;
	}

	public void setErrors(Map<String, String> errors)
	{
		this.errors = errors;
	}

	@Override
	public String toString()
	{
		return "SearchForm{" +
				       "from='" + from + '\'' +
				       ", to='" + to + '\'' +
				       ", klasa=" + klasa +
				       ", ilosc=" + ilosc +
				       ", ilosc_dz=" + ilosc_dz +
				       ", ilosc_inf=" + ilosc_inf +
				       ", data='" + data + '\'' +
				       ", direct=" + direct +
				       ", errors=" + errors +
				       '}';
	}

	public DbLotniskoEntity getFromLotnisko()
	{
		return fromLotnisko;
	}

	public DbLotniskoEntity getToLotnisko()
	{
		return toLotnisko;
	}

	public DbKlasaEntity getKlasaDb()
	{
		return klasaDb;
	}
}
