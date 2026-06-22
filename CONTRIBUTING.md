# Contributing Guide

This document defines how our team of 5 collaborates on this repo. Follow it so everyone's contribution is visible and we avoid merge conflicts.

## Branching
- `main` is protected — no direct pushes.
- Each member works on their own feature branch:
  - `feature/auth`
  - `feature/match`
  - `feature/seating`
  - `feature/booking`
  - `feature/dashboard`

## Workflow
1. Pull latest `main` before starting work each session: `git pull origin main`
2. Make changes only inside your assigned module folder (`model/`, `exceptions/`, and `util/` are shared — discuss before changing).
3. Commit often, with clear messages:
   - Good: `"Add seat double-booking validation to SeatSelectionPanel"`
   - Bad: `"update"`, `"fix stuff"`
4. Push your branch: `git push origin feature/your-module`
5. Open a Pull Request into `main`. Tag at least one teammate to review.
6. Once approved, merge. Delete the branch after merging.

## Shared Files Policy
`model/`, `exceptions/`, and `util/` are used by everyone. If you need to change something here:
- Post in the group chat before editing
- Keep the change backward-compatible if possible
- Pull immediately after it's merged so your branch doesn't drift

## Commit Frequency
Aim for at least 3-4 commits per person per week during active development. This is what shows up in the GitHub contribution graph — consistent small commits look (and are) better than one giant dump at the end.

## Code Review Checklist (before approving a PR)
- [ ] Code compiles without errors
- [ ] New entities/methods have proper encapsulation (private fields, public getters/setters)
- [ ] User-facing inputs are validated (see `util/ValidationUtil.java`)
- [ ] Errors are handled with appropriate custom exceptions, not silently swallowed
- [ ] No hardcoded credentials or test data left in for the final submission
