package net.runelite.client.plugins.botutils;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.MenuOpcode;
import net.runelite.api.Point;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

@Slf4j
@Singleton
public class InterfaceUtils
{
	@Inject
	private Client client;

	@Inject
	private MouseUtils mouse;

	@Inject
	private CalculationUtils calc;

	@Inject
	private MenuUtils menu;

	@Inject
	private ExecutorService executorService;

	public void logout()
	{
		int param1 = (client.getWidget(WidgetInfo.LOGOUT_BUTTON) != null) ? 11927560 : 4522007;
		menu.setEntry(new MenuEntry("", "", 1, MenuOpcode.CC_OP.getId(), -1, param1, false));
		Widget logoutWidget = client.getWidget(WidgetInfo.LOGOUT_BUTTON);
		if (logoutWidget != null)
		{
			mouse.delayMouseClick(logoutWidget.getBounds(), calc.getRandomIntBetweenRange(5, 200));
		}
		else
		{
			mouse.delayMouseClick(new Point(0, 0), calc.getRandomIntBetweenRange(5, 200));
		}
	}

	static void resumePauseWidget(int widgetId, int arg){
		final int garbageValue = 1292618906;
		final String className = "ln";
		final String methodName = "hs";

		try {

			Class clazz = Class.forName(className);
			Method method = clazz.getDeclaredMethod(methodName, int.class, int.class, int.class);
			method.setAccessible(true);
			method.invoke(null, widgetId, arg, garbageValue);
		} catch (Exception ignored) {
			return;
		}
	}

	public WidgetInfo getSpellWidgetInfo(String spell)
	{
		assert client.isClientThread();
		return Spells.getWidget(spell);
	}

	public WidgetInfo getPrayerWidgetInfo(String spell)
	{
		assert client.isClientThread();
		return PrayerMap.getWidget(spell);
	}

	public Widget getSpellWidget(String spell)
	{
		assert client.isClientThread();
		return client.getWidget(Spells.getWidget(spell));
	}

	public Widget getPrayerWidget(String spell)
	{
		assert client.isClientThread();
		return client.getWidget(PrayerMap.getWidget(spell));
	}

	public int getTabHotkey(Tab tab)
	{
		assert client.isClientThread();

		final int var = client.getVarbitValue(client.getVarps(), tab.getVarbit());
		final int offset = 111;

		switch (var)
		{
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
				return var + offset;
			case 13:
				return 27;
			default:
				return -1;
		}
	}

}
