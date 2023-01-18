package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class ScytheClaw extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:ScytheClaw";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/ScytheClaw.png";

    ScytheClaw(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target){
        super( id,  name,  img,  cost,  rawDescription,  type,  color,  rarity,  target);
    }

    public ScytheClaw(boolean canPreview) {
        this(BASE_ID, STRINGS.NAME, IMG, 3, STRINGS.DESCRIPTION, CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.GREEN_ATTACK_CARD, DeadCellsModInitializer.GREEN_ATTACK_CARD_PORTRAIT);

        this.exhaust = true;
        this.baseDamage = 14;
        this.magicNumber = this.baseMagicNumber = 3;
        if (canPreview)
            this.cardsToPreview = new ScytheClawII(false);
    }

    public ScytheClaw(){
        this(true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(6);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("deadCells:SCYTHE_CLAW"));
        addToBot(new WaitAction(0.2F));
        addToBot(new MakeTempCardInDrawPileAction(new ScytheClawII(),1,true,true,false));
        addToBot(new DamageAllEnemiesAction(abstractPlayer,this.baseDamage,this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        for (AbstractCreature monster : AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(monster,abstractPlayer,new VulnerablePower(monster,magicNumber,false),magicNumber,true));
        }

    }


}