package deadCellsMod.cn.infinite.stsmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class LightningBolt extends DeadCellsCard {
    public static final String ID = "deadCells:LightningBolt";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String IMG_URL = "img/card/LightningBolt.png";
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final int BASE_COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int UPGRADE_DMA = 3;
    private static final int DMG_ADD = 7;

    private static final Color GOLD_GLOW = Color.GOLD.cpy();
    private static final Color ORANGE_GLOW = Color.ORANGE.cpy();
    private static final Color RED_GLOW = Color.RED.cpy();
    private boolean canAdd = true;

    public LightningBolt() {
        super(ID, NAME, IMG_URL, BASE_COST, DESCRIPTION, TYPE,
                AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.COMMON,
                CardTarget.ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.PURPLE_ATTACK_CARD, DeadCellsModInitializer.PURPLE_ATTACK_CARD_PORTRAIT);
        this.baseDamage = 7;
        this.baseMagicNumber = 0;

        this.damageType = DamageInfo.DamageType.NORMAL;
    }

    public void triggerOnGlowCheck() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()){
            //第一次
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            this.canAdd = false;
        } else if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1)).cardID == ID && this.baseMagicNumber == 1) {
            //第二次
            this.glowColor = GOLD_GLOW.cpy();
            this.canAdd = false;
        } else if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1)).cardID == ID && this.baseMagicNumber == 2) {
            //第三次
            this.glowColor = ORANGE_GLOW.cpy();
            this.canAdd = false;
        } else if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1)).cardID == ID && this.baseMagicNumber >= 3) {
            //第三次
            this.glowColor = RED_GLOW.cpy();
            this.canAdd = false;
        }else{
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            this.canAdd = true;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * DMG_ADD;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(UPGRADE_DMA);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        this.baseMagicNumber ++;
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        if(this.baseMagicNumber > 3){
            AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.baseMagicNumber, DamageInfo.DamageType.THORNS));
            this.baseMagicNumber = 0;
        }
        this.canAdd = true;
    }

    @Override
    public void onMoveToDiscard() {
        if (!canAdd){
            if (AbstractDungeon.player.hand.size()<10) {
                AbstractDungeon.player.discardPile.moveToHand(this);
            }
            canAdd = true;
        }
    }
}
