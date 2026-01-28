export interface ChampionEntity {
  id: string;
  name: string;
  title: string;
  partType: string;
  blurb: string;
  tags: string[];
  image: string;
}

export interface ChampionDetailsEntity
  extends ChampionEntity, Pick<ChampionDetailsDTO, 'lore' | 'spells' | 'passive' | 'skins'> {}

export interface FetchChampionsApiResponse {
  data: ChampionDTO[];
  format: string;
  type: string;
  version: string;
}

export interface FetchChampionByNameApiResponse extends Omit<FetchChampionsApiResponse, 'data'> {
  data: ChampionDetailsDTO;
}

export interface ChampionDetailsDTO {
  id: string;
  key: string;
  name: string;
  title: string;
  image: Image;
  skins: Skin[];
  lore: string;
  blurb: string;
  allytips: string[];
  enemytips: string[];
  tags: string[];
  partype: string;
  info: Info;
  stats: Stats;
  spells: Spell[];
  passive: Passive;
  recommended: any[];
}

export interface ChampionDTO {
  version: string;
  id: string;
  key: string;
  name: string;
  title: string;
  blurb: string;
  info: Info;
  image: Image;
  tags: string[];
  partype: string;
  stats: Stats;
}

export interface Info {
  attack: number;
  defense: number;
  magic: number;
  difficulty: number;
}

export interface Image {
  full: string;
  sprite: string;
  group: string;
  x: number;
  y: number;
  w: number;
  h: number;
}

export interface Stats {
  hp: number;
  hpperlevel: number;
  mp: number;
  mpperlevel: number;
  movespeed: number;
  armor: number;
  armorperlevel: number;
  spellblock: number;
  spellblockperlevel: number;
  attackrange: number;
  hpregen: number;
  hpregenperlevel: number;
  mpregen: number;
  mpregenperlevel: number;
  crit: number;
  critperlevel: number;
  attackdamage: number;
  attackdamageperlevel: number;
  attackspeedperlevel: number;
  attackspeed: number;
}

export interface Skin {
  id: string;
  num: number;
  name: string;
  chromas: boolean;
}

export interface Spell {
  id: string;
  name: string;
  description: string;
  tooltip: string;
  leveltip: Leveltip;
  maxrank: number;
  cooldown: number[];
  cooldownBurn: string;
  cost: number[];
  costBurn: string;
  datavalues: Datavalues;
  effect: number[] | undefined[];
  effectBurn: string | undefined[];
  vars: any[];
  costType: string;
  maxammo: string;
  range: number[];
  rangeBurn: string;
  image: Image;
  resource: string;
}

export interface Leveltip {
  label: string[];
  effect: string[];
}

export interface Datavalues {}

export interface Passive {
  name: string;
  description: string;
  image: Image;
}

export const convertChampionDtoToEntity = (championDto: ChampionDTO): ChampionEntity => {
  return {
    id: championDto.id,
    name: championDto.name,
    title: championDto.title,
    partType: championDto.partype,
    blurb: championDto.blurb,
    tags: championDto.tags,
    image: `https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championDto.name}_0.jpg`,
  };
};

export const convertChampionDetailsDtoToEntity = (
  championDetailsDto: ChampionDetailsDTO,
): ChampionDetailsEntity => {
  return {
    id: championDetailsDto.id,
    name: championDetailsDto.name,
    title: championDetailsDto.title,
    partType: championDetailsDto.partype,
    blurb: championDetailsDto.blurb,
    tags: championDetailsDto.tags,
    image: `https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championDetailsDto.name}_0.jpg`,
    lore: championDetailsDto.lore,
    spells: getSpellsWithSplash(championDetailsDto.spells),
    passive: getPassiveWithImage(championDetailsDto.passive),
    // TODO: loop over skins to get correct skin splash url for each
    skins: championDetailsDto.skins,
  };
};

const getSpellsWithSplash = (spells: Spell[]): Spell[] => {
  spells.forEach((spell) => {
    spell.image.full = getSpellSplash(spell);
  });

  return spells;
};

const getSpellSplash = (spell: Spell): string => {
  return `https://ddragon.leagueoflegends.com/cdn/16.2.1/img/spell/${spell.image.full}`;
};

const getPassiveWithImage = (passive: Passive): Passive => {
  return {
    ...passive,
    image: {
      ...passive.image,
      full: `https://ddragon.leagueoflegends.com/cdn/16.2.1/img/passive/${passive.image.full}`
    }
  }
}
