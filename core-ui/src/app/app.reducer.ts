import { ActionReducerMap, createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromAuth from './system/auth.reducer';

export interface State {
  // ui: fromUi.State;
  auth: fromAuth.State;
}

export const reducers: ActionReducerMap<State> = {
  // ui: fromUi.uiReducer,
  auth: fromAuth.authReducer
};

// UI
// export const getUiState = createFeatureSelector<fromUi.State>('ui');

// export const getIsLoading = createSelector(getUiState, fromUi.getIsLoading);

// AUTH
export const getAuthState = createFeatureSelector<fromAuth.State>('auth');

export const getIsAuth = createSelector(getAuthState, fromAuth.getIsAuth);
