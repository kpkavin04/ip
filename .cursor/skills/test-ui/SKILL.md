---
name: test-ui
description: Runs Alfred console UI tests defined in test/ui-test-plan.md, compares expected output exactly, and reports the test session. Use after changing Alfred commands, task output, or console interaction.
---

# Test UI

Use this skill after any code update that changes Alfred's console behavior.

## Test plan

Keep test cases in `test/ui-test-plan.md`. Each case must use this format:

````markdown
## Test name

**Aim:** What this test verifies.

### Input
```text
command one
command two
```

### Expected output
```text
exact program output
```
````

Update the plan whenever a behavior changes or a new command is added.

## Run tests

From the project root, run:

```bash
python3 .cursor/skills/test-ui/scripts/run_ui_tests.py
```

The runner compiles all files in `src/main/java`, runs every test case in plan order, and compares program output exactly with the expected output.

## Results

For each case, show its aim, console input, and console output. Stop immediately at the first failure and report both expected and actual output. Do not continue to later cases after a failure.
