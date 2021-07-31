package deadCellsMod.cn.infinite.stsmod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Timer;
import java.util.TimerTask;

public class ScytheClawII extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:ScytheClawII";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/ScytheClawII.png";

    public ScytheClawII(boolean canPreview){
        super(BASE_ID,STRINGS.NAME,IMG,3,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ALL_ENEMY);

        this.exhaust = true;
        this.baseDamage = 32;
        this.damageType = DamageInfo.DamageType.NORMAL;
        this.magicNumber = this.baseMagicNumber = (int)(this.baseDamage*1.5);

        if (canPreview)
            this.cardsToPreview = new ScytheClaw(false);
    }

    public ScytheClawII() {
        this(true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(6);
            this.upgradeMagicNumber(9);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("deadCells:SCYTHE_CLAW"));
        addToBot(new MakeTempCardInDrawPileAction(new ScytheClaw(),1,true,true,false));
        addToBot(new DamageAllEnemiesAction(abstractPlayer,this.baseDamage,this.damageType, AbstractGameAction.AttackEffect.SLASH_HEAVY));

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCreature monster : AbstractDungeon.getMonsters().monsters) {
                    System.out.println(monster.lastDamageTaken);
                    if (!monster.isDead && monster.lastDamageTaken >= magicNumber) {
                        addToBot(new StunMonsterAction((AbstractMonster) monster, AbstractDungeon.player));
                    }
                }
                isDone = true;
            }
        });
    }

}
