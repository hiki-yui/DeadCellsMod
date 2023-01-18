package deadCellsMod.cn.infinite.stsmod.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import deadCellsMod.cn.infinite.stsmod.cards.FerrymanSLantern;
import deadCellsMod.cn.infinite.stsmod.character.King;


@SpirePatch(
        clz = AbstractPlayer.class,
        method = "onVictory"
)
public class FerrymanSLanternPatch {
    @SpireInsertPatch(
            loc = 2549
    )
    public static void insert(AbstractPlayer _instance){
        if (_instance.getClass() == King.class){
            FerrymanSLantern.AmNumber = 0;
        }
    }
}
