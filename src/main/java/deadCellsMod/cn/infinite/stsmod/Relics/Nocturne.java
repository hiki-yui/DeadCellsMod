package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import deadCellsMod.cn.infinite.stsmod.powers.NocturneAttackedPower;

//夜歌
public class Nocturne extends CustomRelic {
    public static final String BASE_ID = "deadCells:Nocturne";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/nocturne.png";

    public Nocturne(){
        super(BASE_ID,new Texture(IMG),RelicTier.RARE,LandingSound.CLINK);
        this.counter = 0;
    }


    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + 5 +//cardNum
                STRINGS.DESCRIPTIONS[1] + 1 +
                STRINGS.DESCRIPTIONS[2] + 5 +//damage
                STRINGS.DESCRIPTIONS[3] + 1 +
                STRINGS.DESCRIPTIONS[4];

        /*
          "你每打出 #b",
          " 技能，随机对 #b",
          " 名敌人造成 #y",
          " 点伤害，并给予该敌人 #b ",
          " 层 夜歌标记 "
          */
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type== AbstractCard.CardType.SKILL){
            this.counter++;
            int num = 5;
            if (counter == num){
                this.flash();
                AbstractCreature player = AbstractDungeon.player;
                AbstractMonster monster = AbstractDungeon.getRandomMonster();
                if (monster!=null){
                    int baseDamage = 5;
                    addToBot(new DamageAction(monster,new DamageInfo(player, baseDamage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.LIGHTNING));
                    if (!monster.isDead) {
                        addToBot(new ApplyPowerAction(monster, player, new NocturneAttackedPower(monster, 1), 1));
                    }
                    counter=0;
                }
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Nocturne();
    }
}
