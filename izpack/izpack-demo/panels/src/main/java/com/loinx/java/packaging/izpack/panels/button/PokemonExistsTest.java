/**
 * 
 */
package com.loinx.java.packaging.izpack.panels.button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.handler.Prompt;
import com.izforge.izpack.panels.userinput.action.ButtonAction;
import com.izforge.izpack.util.Console;

/**
 * @author loinx
 *
 */
public class PokemonExistsTest extends ButtonAction {
	private static final String ERROR = "error";
	private static final String POKEMON_DATABSE = "http://www.pokeapi.co/api/v1/pokemon/";

	public PokemonExistsTest(InstallData installData) {
		super(installData);
	}

	@Override
	public boolean execute() {
		try {
			String pokemon = installData.getVariable("pokedex.pokemon").toLowerCase();
			if (pokemon == null || pokemon.isEmpty()) {
				return false;
			}
			URL url = new URL(POKEMON_DATABSE + pokemon);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode >= 200 && responseCode < 300) {
				return true;
			}
		} catch (MalformedURLException e) {
			// Could not generate an URL
		} catch (IOException e) {
			// Could not ping address
		}
		return false;
	}

	@Override
	public boolean execute(Console console) {
		if (!execute()) {
			console.println(messages.get(ERROR));
			return false;
		}
		return true;
	}

	@Override
	public boolean execute(Prompt prompt) {
		if (!execute()) {
			prompt.warn(messages.get(ERROR));
			return false;
		}
		return true;
	}
}