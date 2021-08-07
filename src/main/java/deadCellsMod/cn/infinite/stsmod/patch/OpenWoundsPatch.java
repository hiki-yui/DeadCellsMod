package deadCellsMod.cn.infinite.stsmod.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import deadCellsMod.cn.infinite.stsmod.Relics.OpenWounds;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "damage",
        paramtypez = {
                DamageInfo.class
        }
)
public class OpenWoundsPatch {

    @SpireInsertPatch(
            loc = 798,
            localvars = {"damageAmount","info"}
            )
    public static void insert(AbstractMonster __instance,int damageAmount,DamageInfo info){
        for (AbstractRelic relic : AbstractDungeon.player.relics){
            if (relic.getClass() == OpenWounds.class){
                if ( __instance.currentBlock < damageAmount &&
                        AbstractDungeon.player.equals(info.owner) &&
                        info.type == DamageInfo.DamageType.NORMAL){
                    AbstractDungeon.actionManager.addToBottom(
                            new GainBleedingPowerAction(null,__instance,((OpenWounds)relic).getAmount()));
                }
            }
        }
    }
}
