package deadCellsMod.cn.infinite.stsmod.cards;

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

public class CrowBar extends DeadCellsCard {
    public static final String ID = "deadCells:CrowBar";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String IMG_URL = "img/card/CrowBar.png";
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final int BASE_COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int UPGRADE_DMA = 2;
    private boolean doubleDamage;

    public CrowBar(){
        super(ID,NAME,IMG_URL,BASE_COST,DESCRIPTION,TYPE,
                AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.SPECIAL,
                CardTarget.ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED_ATTACK_CARD, DeadCellsModInitializer.RED_ATTACK_CARD_PORTRAIT);
        this.doubleDamage = false;
        this.baseDamage = 8;
        this.damageType = DamageInfo.DamageType.NORMAL;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(UPGRADE_DMA);
            this.upgradeName();
        }
    }

    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            this.doubleDamage = true;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            this.doubleDamage = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.doubleDamage){
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }else{
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        this.doubleDamage = false;
    }
}
