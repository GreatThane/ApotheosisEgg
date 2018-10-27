package org.thane.patches;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch(clz = AbstractRoom.class, method="spawnRelicAndObtain", paramtypez = {
        float.class, float.class, AbstractRelic.class
})
public class SpawnRelicFix {
    @SpirePostfixPatch
    public static void spawnRelicAndObtain(AbstractRoom room, float x, float y, AbstractRelic relic) {
        if (Loader.DEBUG) {
            BaseMod.publishRelicGet(relic);
        }
    }
}
