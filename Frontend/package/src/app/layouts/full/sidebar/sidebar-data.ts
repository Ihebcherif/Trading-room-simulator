import { NavItem } from './nav-item/nav-item';

export const navItems: NavItem[] = [
  {
    navCap: 'Home',
  },
  {
    displayName: 'Dashboard',
    iconName: 'layout-dashboard',
    bgcolor: 'primary',
    route: '/dashboard',
  },
  {
    navCap: 'Trading',
  },
  {
    displayName: 'Formation',
    iconName: 'rosette',
    bgcolor: 'primary',
    route: '/ui-components/Formation',
  },
  {
    displayName: 'Actuariat',
    iconName: 'poker-chip',
    bgcolor: 'primary',
    route: '/ui-components/Actuariat',
  },
  {
    displayName: 'Quizz',
    iconName: 'list',
    bgcolor: 'primary',
    route: '/ui-components/Quizz',
  },
  {
    displayName: 'Concours',
    iconName: 'layout-navbar-expand',
    bgcolor: 'primary',
    route: '/ui-components/Concours',
  },
  {
    displayName: 'Contract',
    iconName: 'tooltip',
    bgcolor: 'primary',
    route: '/ui-components/Contract',
  },

  {
    displayName: 'Ordre',
    iconName: 'mood-smile',
    bgcolor: 'primary',
    route: '/ui-components/Ordre',
  },
  {
    displayName: 'PorteFeuille',
    iconName: 'aperture',
    bgcolor: 'primary',
    route: '/ui-components/PorteFeuille',
  },
  {
    displayName: 'Portfolio-risk',
    iconName: 'aperture',
    bgcolor: 'primary',
    route: '/ui-components/Portfolio-risk',
  },
  {
    displayName: 'Prediction',
    iconName: 'aperture',
    bgcolor: 'primary',
    route: '/ui-components/Prediction',
  },
];
