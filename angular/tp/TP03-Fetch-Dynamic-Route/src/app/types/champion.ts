export interface ChampionEntity {
    id: string;
    name: string;
    title: string;
    partType: string;
    blurb: string;
    tags: string[];
    image: string;
}

export interface ApiResponse {
    data: ChampionDTO[],
    format: string;
    type: string;
    version: string;
}

export interface ChampionDTO {
  version: string
  id: string
  key: string
  name: string
  title: string
  blurb: string
  info: Info
  image: Image
  tags: string[]
  partype: string
  stats: Stats
}

export interface Info {
  attack: number
  defense: number
  magic: number
  difficulty: number
}

export interface Image {
  full: string
  sprite: string
  group: string
  x: number
  y: number
  w: number
  h: number
}

export interface Stats {
  hp: number
  hpperlevel: number
  mp: number
  mpperlevel: number
  movespeed: number
  armor: number
  armorperlevel: number
  spellblock: number
  spellblockperlevel: number
  attackrange: number
  hpregen: number
  hpregenperlevel: number
  mpregen: number
  mpregenperlevel: number
  crit: number
  critperlevel: number
  attackdamage: number
  attackdamageperlevel: number
  attackspeedperlevel: number
  attackspeed: number
}

export const convertChampionDtoToEntity = (championDto: ChampionDTO): ChampionEntity => {
    return {
        id: championDto.id,
        name: championDto.name,
        title: championDto.title,
        partType: championDto.partype,
        blurb: championDto.blurb,
        tags: championDto.tags,
        image: championDto.image.full
    }
}