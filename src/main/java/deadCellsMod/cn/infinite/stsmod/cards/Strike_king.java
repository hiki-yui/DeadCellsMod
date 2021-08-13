package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;


public class Strike_king extends CustomCard {

    public static final String ID = "king:Strike";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String IMG_URL = "img/card/strike.png";
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final int BASE_COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int UPGRADE_DMA = 3;

    public Strike_king(){
        super(ID,NAME,IMG_URL,BASE_COST,DESCRIPTION,TYPE,
                AbstractDeadCellsEnum.DEAD_CELLS/*CardColor.BLUE*/,CardRarity.BASIC/*CardRarity.COMMON*/,
                CardTarget.ENEMY);

        this.baseDamage = 6;
        /*this.baseDamage = 996;*/
        this.damageType = DamageInfo.DamageType.NORMAL;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(UPGRADE_DMA);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(paramAbstractMonster,
                        new DamageInfo(paramAbstractPlayer,this.damage),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

   /* @Override
    public AbstractCard makeCopy(){
        AbstractCard card = new Strike_king();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
